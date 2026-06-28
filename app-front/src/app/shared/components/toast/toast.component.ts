import { Component, inject } from '@angular/core';
import { ToastService } from '../../services/toast.service';

@Component({
  selector: 'app-toast',
  standalone: true,
  template: `
    <div class="toast-container">
      @for (toast of toastService.toasts(); track toast.id) {
        <div class="toast toast--{{ toast.type }}" (click)="toastService.dismiss(toast.id)">
          <span class="toast-icon">{{ icon(toast.type) }}</span>
          <span class="toast-message">{{ toast.message }}</span>
          <button class="toast-close" aria-label="Fechar">✕</button>
        </div>
      }
    </div>
  `,
  styleUrl: './toast.component.css'
})
export class ToastComponent {
  toastService = inject(ToastService);

  icon(type: string): string {
    const icons: Record<string, string> = {
      success: '✓', error: '✕', warning: '⚠', info: 'ℹ'
    };
    return icons[type] ?? 'ℹ';
  }
}
