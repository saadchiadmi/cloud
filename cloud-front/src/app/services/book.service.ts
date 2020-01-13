import { Injectable, Inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, BehaviorSubject } from 'rxjs';
import { Book } from '../entities/Book';

@Injectable({
  providedIn: 'root'
})
export class BookService {

  URI : string = "http://localhost:8080";

  constructor(private http: HttpClient) { } 

  book : BehaviorSubject<Book[]> = new BehaviorSubject([
    {name: 'saad'},
    {name: 'laurie'},
  ]);

  getBooks(): Observable< Book[]> {
    return this.http.get<Book[]>(this.URI+"/books");
    //return this.book;
  }

  getBookByName(book : string): Observable< Book>{
      return this.book[0];
  }
}
