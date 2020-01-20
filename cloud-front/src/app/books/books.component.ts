import { Component, OnInit } from '@angular/core';
import { BookService } from '../services/book.service';
import { Book } from '../entities/Book';
import { SelectItem } from 'primeng/api/selectitem';

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

  sortField: string;

  sortOrder: number;

  constructor(private bookService : BookService) { }

  ngOnInit() {
    this.bookService.getBooks().subscribe(res => this.books=res);
  }

    selectbook(event: Event, book: Book) {
      this.selectedBook = book;
      this.displayDialog = true;
      event.preventDefault();
  }

  onSortChange(event) {
      let value = event.value;

      if (value.indexOf('!') === 0) {
          this.sortOrder = -1;
          this.sortField = value.substring(1, value.length);
      }
      else {
          this.sortOrder = 1;
          this.sortField = value;
      }
  }

  onDialogHide() {
      this.selectedBook = null;
  }

  search(word){
    this.bookService.getBookByName(word).subscribe(res => this.books = res);
  }

}
