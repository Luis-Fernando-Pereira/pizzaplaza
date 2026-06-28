import { inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { Observable, tap } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class AuthService {

  private readonly TOKEN_KEY = 'authToken';

  private http = inject(HttpClient);
  private router = inject(Router);

  login(email: string, password: string): Observable<string> {
    return this.http
      .post('/api/auth/login', { email, password }, { responseType: 'text' })
      .pipe(tap(token => this.saveToken(token)));
  }

  logout(): void {
    const token = this.getToken();
    if (token) {
      this.http.post('/api/auth/logout', null).subscribe();
    }
    localStorage.removeItem(this.TOKEN_KEY);
    this.router.navigate(['/admin/login']);
  }

  getToken(): string | null {
    return localStorage.getItem(this.TOKEN_KEY);
  }

  isAuthenticated(): boolean {
    return !!this.getToken();
  }

  getUserFromToken(): { oid: string; name: string } | null {
    const token = this.getToken();
    if (!token) return null;
    try {
      const payload = JSON.parse(atob(token.split('.')[1]));
      return { oid: payload.sub, name: payload.name };
    } catch {
      return null;
    }
  }

  private saveToken(token: string): void {
    localStorage.setItem(this.TOKEN_KEY, token);
  }
}
