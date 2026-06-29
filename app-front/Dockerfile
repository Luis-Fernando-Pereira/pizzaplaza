# Etapa de build
FROM node:22-alpine AS build

WORKDIR /app

COPY app-front/package*.json ./

RUN npm ci

COPY app-front/ .

RUN npm run build

# Etapa de execução
FROM nginx:alpine

COPY app-front/nginx.conf /etc/nginx/conf.d/default.conf

COPY --from=build /app/dist/app-front/browser /usr/share/nginx/html

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]
