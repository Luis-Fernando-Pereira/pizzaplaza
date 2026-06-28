import { HttpErrorResponse } from '@angular/common/http';

export function extractErrorMessage(err: HttpErrorResponse, fallback: string): string {
  if (typeof err.error === 'string' && err.error.trim()) {
    return err.error.trim();
  }
  if (err.error?.error && typeof err.error.error === 'string') {
    return err.error.error;
  }
  if (Array.isArray(err.error?.violations) && err.error.violations.length) {
    return (err.error.violations as { message: string }[])
      .map(v => v.message)
      .join('. ');
  }
  return fallback;
}
