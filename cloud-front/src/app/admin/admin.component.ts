import { Component, OnInit } from '@angular/core';
import {MessageService} from 'primeng/api';
import {ConfirmationService} from 'primeng/api';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css'],
  providers: [MessageService, ConfirmationService]
})
export class AdminComponent implements OnInit {

  uploadedFiles: any[] = [];
  check : boolean = false;
  data: any;
  dataBar: any;

  constructor(private messageService: MessageService, private confirmationService: ConfirmationService) { }

  ngOnInit() {
    
  }

  onUpload(event) {
    for(let file of event.files) {
        this.uploadedFiles.push(file);
    }
    this.messageService.add({severity: 'info', summary: 'Add', detail:'File Selected'});
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
          this.check = true;
          this.data = {
            labels: ['Indexing','Jaccard','Closeness'],
            datasets: [
                {
                    data: [300, 50, 100],
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
            labels: ['Indexing','Jaccard','Closeness'],
            datasets: [
                {
                    data: [150, 50, 100],
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
        }
    });
}

}
