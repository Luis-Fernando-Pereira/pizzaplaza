import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { AdminFormComponent } from '../../components/admin-form/admin-form';

@Component({
  selector: 'app-admin-create-page',
  standalone: true,
  imports: [RouterLink, AdminFormComponent],
  templateUrl: './admin-create-page.html',
  styleUrl: './admin-create-page.css'
})
export class AdminCreatePage {}
