import { HttpClient } from "@angular/common/http";

export class Urls {
   public static get BASE_URL():string { return "http://localhost:8080/"; }
   
    constructor(httpClient: HttpClient) {         
    }
}
