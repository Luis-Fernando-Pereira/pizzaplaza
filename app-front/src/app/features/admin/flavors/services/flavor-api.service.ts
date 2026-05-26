import { inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Flavor } from '../models/flavor.model';
import { CreateFlavorRequest } from '../models/create-flavor-request';

@Injectable({ providedIn: 'root' })
export class FlavorApiService {

  private http = inject(HttpClient);

  private readonly API =
    'http://localhost:8083/flavors';

  findAll(): Observable<Flavor[]> {

    return this.http.get<Flavor[]>(this.API);

  }

  findByOid(oid: string): Observable<Flavor> {

    return this.http.get<Flavor>(
      `${this.API}/${oid}`
    );

  }

  create(
    request: CreateFlavorRequest
  ): Observable<Flavor> {

    return this.http.post<Flavor>(
      this.API,
      request
    );

  }

  update(request: CreateFlavorRequest): Observable<Flavor> {

    return this.http.put<Flavor>(
      `${this.API}`,
      request
    );

  }

  delete(oid: string): Observable<void> {

    return this.http.delete<void>(
      `${this.API}/${oid}`
    );

  }

}
