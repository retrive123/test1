import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IAuthenticKey } from 'app/shared/model/authentic-key.model';

type EntityResponseType = HttpResponse<IAuthenticKey>;
type EntityArrayResponseType = HttpResponse<IAuthenticKey[]>;

@Injectable({ providedIn: 'root' })
export class AuthenticKeyService {
    public resourceUrl = SERVER_API_URL + 'api/authentic-keys';

    constructor(private http: HttpClient) {}

    create(authenticKey: IAuthenticKey): Observable<EntityResponseType> {
        return this.http.post<IAuthenticKey>(this.resourceUrl, authenticKey, { observe: 'response' });
    }

    update(authenticKey: IAuthenticKey): Observable<EntityResponseType> {
        return this.http.put<IAuthenticKey>(this.resourceUrl, authenticKey, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IAuthenticKey>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IAuthenticKey[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
