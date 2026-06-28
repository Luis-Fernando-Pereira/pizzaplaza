import { computed, inject, Injectable, signal } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { Observable, tap } from 'rxjs';

export interface UserSession {
  oid: string;
  name: string;
  email: string;
  role: 'admin' | 'seller' | 'client';
}

@Injectable({ providedIn: 'root' })
export class AuthService {

  private readonly TOKEN_KEY = 'authToken';

  private http   = inject(HttpClient);
  private router = inject(Router);

  currentUser = signal<UserSession | null>(this.parseToken());
  isLoggedIn  = computed(() => this.currentUser() !== null);
  userRole    = computed(() => this.currentUser()?.role ?? null);

  get initials(): string {
    const name = this.currentUser()?.name ?? '';
    return name.split(' ').map((w: string) => w[0]).join('').slice(0, 2).toUpperCase() || '?';
  }

  login(email: string, password: string): Observable<string> {
    return this.http
      .post('/api/auth/login', { email, password }, { responseType: 'text' })
      .pipe(tap(token => this.saveToken(token)));
  }

  logout(): void {
    const role  = this.userRole();
    const token = this.getToken();
    if (token) {
      this.http.post('/api/auth/logout', null).subscribe();
    }
    sessionStorage.removeItem(this.TOKEN_KEY);
    this.currentUser.set(null);
    if (role === 'admin' || role === 'seller') {
      this.router.navigate(['/admin/login']);
    } else {
      this.router.navigate(['/login']);
    }
  }

  getToken(): string | null {
    return sessionStorage.getItem(this.TOKEN_KEY);
  }

  private saveToken(token: string): void {
    sessionStorage.setItem(this.TOKEN_KEY, token);
    this.currentUser.set(this.parseToken());
  }

  private parseToken(): UserSession | null {
    const token = sessionStorage.getItem(this.TOKEN_KEY);
    if (!token) return null;
    try {
      const payload = JSON.parse(atob(token.split('.')[1]));
      if (payload.exp && payload.exp * 1000 < Date.now()) {
        sessionStorage.removeItem(this.TOKEN_KEY);
        return null;
      }
      const groups: string[] = payload.groups ?? [];
      const role = groups[0] as UserSession['role'];
      if (!role) return null;
      return { oid: payload.sub, name: payload.name ?? '', email: payload.upn ?? '', role };
    } catch {
      return null;
    }
  }
}
