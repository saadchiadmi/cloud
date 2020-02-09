import { Component, OnInit } from '@angular/core';
import { BookService } from '../services/book.service';
import { Book } from '../entities/Book';
import { SelectItem } from 'primeng/api/selectitem';
import {Subscription} from 'rxjs';

@Component({
  selector: 'app-books',
  templateUrl: './books.component.html',
  styleUrls: ['./books.component.css']
})
export class BooksComponent implements OnInit {

  books : Book[];
  selectedBook: Book;
  displayDialog: boolean;
  sortOptions: SelectItem[];
  sortKey: string;
  searchInput: string = "";
  selected: string = 'normal';
  sortField: string = "occurence";
  busy: Subscription;

  constructor(private bookService : BookService) { }

  ngOnInit() {
    this.busy = this.bookService.getBooks().subscribe(res => this.books=res);
    this.sortOptions = [
      {label: 'Occurence', value: 'occurence'},
      {label: 'Closeness', value: 'closeness'},
      {label: 'Suggestion', value: 'suggestion'},
  ];
  }

  selectbook(event: Event, book: Book) {
    console.log(book);
    this.selectedBook = book;
    this.displayDialog = true;
    event.preventDefault();
  }

  onSortChange(event) {
      let value = event.value;
      this.search(this.searchInput);
  }

  onRadioChange(){
    this.search(this.searchInput);
  }

  onDialogHide() {
      this.selectedBook = null;
  }

  search(word:string){
    if(this.sortField=="occurence"){
      if(word==""){
        this.busy = this.bookService.getBooks().subscribe(res => this.books = res);
      }
      else if(this.selected=="normal"){
        this.busy = this.bookService.getBookByName(word.toLowerCase()).subscribe(res => this.books = res);
      }
      else if(this.selected=="regex"){
        this.busy = this.bookService.getBookRegexByName(word.toLowerCase()).subscribe(res => this.books = res);
      }
    }
    else if(this.sortField=="closeness"){
      if(word==""){
        this.busy = this.bookService.getBooksCloseness().subscribe(res => this.books = res);
      }
      else if(this.selected=="normal"){
        this.busy = this.bookService.getBooksClosenessByName(word.toLowerCase()).subscribe(res => this.books = res);
      }
      else if(this.selected=="regex"){
        this.busy = this.bookService.getBooksRegexClosenessByName(word.toLowerCase()).subscribe(res => this.books = res);
      }
    }
    else if(this.sortField=="suggestion"){
      if(word==""){
        this.busy = this.bookService.getBooks().subscribe(res => this.books = res);
      }
      else if(this.selected=="normal"){
        this.busy = this.bookService.getBooksSuggestionByName(word.toLowerCase()).subscribe(res => this.books = res);
      }
      else if(this.selected=="regex"){
        this.busy = this.bookService.getBooksRegexSuggestionByName(word.toLowerCase()).subscribe(res => this.books = res);
      }
    }
  }

}
