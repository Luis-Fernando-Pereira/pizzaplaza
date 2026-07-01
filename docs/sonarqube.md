# SonarQube — Guia de uso

Análise de qualidade e cobertura do PizzaPlaza como **um único projeto** Sonar,
cobrindo o backend (Java/Quarkus multimódulo) e o frontend (Angular).

## Componentes

| Item | Arquivo |
|------|---------|
| Servidor (Docker) | `docker-compose.sonar.yml` |
| Cobertura Java (JaCoCo) | `pom.xml` (plugin herdado por todos os módulos) |
| Cobertura frontend (LCOV) | `app-front/angular.json` + `@vitest/coverage-v8` |
| Config do scanner | `sonar-project.properties` |
| CI | `.github/workflows/sonar.yml` |

## 1. Subir o servidor (uma vez)

```bash
# Pré-requisito do Elasticsearch interno do SonarQube
sudo sysctl -w vm.max_map_count=262144   # persista em /etc/sysctl.conf

docker compose -f docker-compose.sonar.yml up -d
```

Acesse http://localhost:9000 (login inicial `admin` / `admin`, troque a senha).
Crie o projeto **manualmente** com a chave `pizzaplaza` e gere um **token de
análise** (My Account > Security, ou no fluxo de criação do projeto).

## 2. Análise local

```bash
# (a) backend: compila e gera os relatórios JaCoCo (target/site/jacoco/jacoco.xml)
./mvnw clean verify

# (b) frontend: gera a cobertura LCOV (app-front/coverage/app-front/lcov.info)
cd app-front && npm test -- --watch=false && cd ..

# (c) os caminhos do LCOV são relativos a app-front; prefixe-os para a raiz
sed -i 's|^SF:|SF:app-front/|' app-front/coverage/app-front/lcov.info

# (d) roda o scanner (precisa do SonarScanner CLI instalado, ou use Docker abaixo)
sonar-scanner -Dsonar.token=SEU_TOKEN
```

Sem instalar o CLI, via Docker:

```bash
docker run --rm --network host \
  -e SONAR_HOST_URL=http://localhost:9000 \
  -e SONAR_TOKEN=SEU_TOKEN \
  -v "$PWD:/usr/src" sonarsource/sonar-scanner-cli
```

## 3. CI (GitHub Actions)

O workflow `sonar.yml` roda em push/PR na `main` e executa os passos acima
automaticamente. Configure os secrets:

- `SONAR_TOKEN` — token de análise do servidor.
- `SONAR_HOST_URL` — URL do SonarQube acessível pelo runner.

> ⚠️ Um servidor self-hosted em `localhost` **não** é acessível pelos runners
> hospedados do GitHub. Opções: usar um *self-hosted runner* na mesma rede,
> expor o SonarQube publicamente (com HTTPS), ou migrar para o SonarCloud.

## Notas técnicas

- **JaCoCo clássico** (não `quarkus-jacoco`): os testes atuais são JUnit/Mockito
  puros (Surefire). O agente é injetado via `argLine` e o relatório XML é gerado
  na fase `verify`.
- O `lcov.info` do Angular usa caminhos `SF:src/...` relativos a `app-front`;
  por isso o `sed` que prefixa `app-front/` antes do scan (monorepo).
- Specs (`*.spec.ts`) são excluídos da análise de código, mas contribuem para a
  cobertura via LCOV.
- `util` compila para Java 25 — o build (local e CI) usa JDK 25.
