export interface AdminUser {
  oid?: string;
  name: string;
  cpf: string;
  email: string;
  password: string;
  userType: 'ADMIN';
}
