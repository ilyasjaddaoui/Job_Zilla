import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HTTP_INTERCEPTORS } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { AuthService } from "./auth.service";


@Injectable()
export class AuthInterceptor implements HttpInterceptor{
    
    constructor(private auth : AuthService){}
    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

        let authReq=req;
        // add jwt token (localStorage) request
        const token= this.auth.getToken();
        if(token!= null) {
            authReq = authReq.clone({setHeaders: {Authorization:`Bearer ${token}`},

        });
        }
        return next.handle(authReq);

    }

}
export const AuthInterceptorProviders = [
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi:true },
];