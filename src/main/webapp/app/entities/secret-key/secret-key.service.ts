import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ISecretKey } from 'app/shared/model/secret-key.model';

type EntityResponseType = HttpResponse<ISecretKey>;
type EntityArrayResponseType = HttpResponse<ISecretKey[]>;

@Injectable({ providedIn: 'root' })
export class SecretKeyService {
    public resourceUrl = SERVER_API_URL + 'api/secret-keys';

    constructor(private http: HttpClient) {}

    create(secretKey: ISecretKey): Observable<EntityResponseType> {
        return this.http.post<ISecretKey>(this.resourceUrl, secretKey, { observe: 'response' });
    }

    update(secretKey: ISecretKey): Observable<EntityResponseType> {
        return this.http.put<ISecretKey>(this.resourceUrl, secretKey, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ISecretKey>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ISecretKey[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
