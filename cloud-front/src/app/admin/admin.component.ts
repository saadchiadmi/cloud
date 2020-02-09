import { Component, OnInit } from '@angular/core';
import {MessageService} from 'primeng/api';
import {ConfirmationService} from 'primeng/api';
import { BookService } from '../services/book.service';
import { TimeExecution } from '../entities/TimeExecution';
import {Subscription} from 'rxjs';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css'],
  providers: [MessageService, ConfirmationService]
})
export class AdminComponent implements OnInit {

  uploadedFiles: any[] = [];
  timeCalcul : TimeExecution;
  check : boolean = false;
  data: any;
  dataBar: any;
  busy: Subscription;

  constructor(private bookService : BookService, private messageService: MessageService, private confirmationService: ConfirmationService) { }

  ngOnInit() {
    
  }

  onSelect(event) {
    for(let file of event.files) {
        this.uploadedFiles.push(file);
    }
    this.messageService.add({severity: 'info', summary: 'Add', detail:'File Selected'});
  }

  onUpload(event) {
    for(let file of this.uploadedFiles) {
      this.messageService.add({severity: 'info', summary: 'Uploud', detail:'File Selected'+file.name});
    }
    this.uploadedFiles = [];
  }

  clear(event) {
    this.uploadedFiles = this.uploadedFiles.filter(file => file !== event.file);
    this.messageService.add({severity:'warn', summary: 'Delete', detail: event.file.name+' File deleted'});
  }

  clearAll() {
    this.uploadedFiles = [];
    this.messageService.add({severity:'warn', summary: 'Delete', detail:'All selected files deleted'});
  }

  confirm() {
    console.log('confirm')
    this.confirmationService.confirm({
        message: 'Are you sure that you want to perform this action?',
        accept: () => {
          this.busy = this.bookService.startCalcul().subscribe(res => {
            this.timeCalcul = res;
            console.log(this.timeCalcul);
            this.check = true;
            this.data = {
              labels: ['Indexing '+this.timeCalcul.index+" MS",'Graphe '+this.timeCalcul.graphe+" MS",'Closeness '+this.timeCalcul.closeness+" MS"],
              datasets: [
                  {
                      data: [this.timeCalcul.index, this.timeCalcul.graphe, this.timeCalcul.closeness],
                      backgroundColor: [
                          "#FF6384",
                          "#36A2EB",
                          "#FFCE56"
                      ],
                      hoverBackgroundColor: [
                          "#FF6384",
                          "#36A2EB",
                          "#FFCE56"
                      ]
                  }]    
            };
            this.dataBar = {
              labels: ['Indexing '+this.timeCalcul.index+" MS",'Graphe '+this.timeCalcul.graphe+" MS",'Closeness '+this.timeCalcul.closeness+" MS"],
              datasets: [
                  {
                      data: [this.timeCalcul.index, this.timeCalcul.graphe, this.timeCalcul.closeness],
                      backgroundColor: [
                          "#1E88E5",
                          "#FFCE56",
                          "#9CCC65"
                      ],
                      hoverBackgroundColor: [
                          "#1E88E5",
                          "#FFCE56",
                          "#9CCC65"
                      ]
                  }]
            }
          });
        }
    });
}

}
