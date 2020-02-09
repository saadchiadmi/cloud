import { Injectable, Inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, BehaviorSubject } from 'rxjs';
import { Book } from '../entities/Book';
import { TimeExecution } from '../entities/TimeExecution';

@Injectable({
  providedIn: 'root'
})
export class BookService {

  URI : string = "http://localhost:8080";

  constructor(private http: HttpClient) { } 

  getBooks(): Observable< Book[]> {
    return this.http.get<Book[]>(this.URI+"/books");
  }

  getBooksCloseness(): Observable< Book[]> {
    return this.http.get<Book[]>(this.URI+"/books/closeness");
  }

  getBookByName(book : string): Observable< Book[]>{
    return this.http.get<Book[]>(this.URI+"/books/"+book);
  }

  getBookRegexByName(book : string): Observable< Book[]>{
    return this.http.get<Book[]>(this.URI+"/books/"+book+"/regex");
  }

  getBooksClosenessByName(book : string): Observable< Book[]>{
    return this.http.get<Book[]>(this.URI+"/books/"+book+"/closeness");
  }

  getBooksRegexClosenessByName(book : string): Observable< Book[]>{
    return this.http.get<Book[]>(this.URI+"/books/"+book+"/regex/closeness");
  }

  getBooksSuggestionByName(book : string): Observable< Book[]>{
    return this.http.get<Book[]>(this.URI+"/books/"+book+"/suggestion");
  }

  getBooksRegexSuggestionByName(book : string): Observable< Book[]>{
    return this.http.get<Book[]>(this.URI+"/books/"+book+"/regex/suggestion");
  }

  startCalcul(): Observable<TimeExecution>{
    return this.http.get<TimeExecution>(this.URI+"/start");
  }
}
