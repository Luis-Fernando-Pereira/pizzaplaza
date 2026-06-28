import { inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, switchMap } from 'rxjs';
import { AdminUser } from '../models/admin-user.model';

@Injectable({ providedIn: 'root' })
export class AdminApiService {

  private readonly USERS_API = '/api/users/admin';
  private readonly AUTH_API = '/api/auth/register';

  private http = inject(HttpClient);

  findAll(): Observable<AdminUser[]> {
    return this.http.get<AdminUser[]>(this.USERS_API);
  }

  findByOid(oid: string): Observable<AdminUser> {
    return this.http.get<AdminUser>(`${this.USERS_API}/${oid}`);
  }

  create(admin: AdminUser): Observable<AdminUser> {
    return this.http
      .post(this.AUTH_API, { ...admin, userType: 'ADMIN' })
      .pipe(switchMap(() => this.http.post<AdminUser>(this.USERS_API, { ...admin, userType: 'ADMIN' })));
  }

  update(admin: AdminUser): Observable<AdminUser> {
    return this.http.put<AdminUser>('/api/users/admin', { ...admin, userType: 'ADMIN' });
  }

  delete(oid: string): Observable<void> {
    return this.http.delete<void>(`${this.USERS_API}/${oid}`);
  }
}
