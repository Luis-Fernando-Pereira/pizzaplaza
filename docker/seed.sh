#!/usr/bin/env bash
# =============================================================
#  PIZZA PLAZA — Seed de dados iniciais
#  Uso:
#    ./docker/seed.sh              # conecta em localhost:5433 (padrão Docker)
#    ./docker/seed.sh 5432         # porta alternativa (dev local sem Docker)
# =============================================================

set -euo pipefail

DB_HOST="${DB_HOST:-localhost}"
DB_PORT="${1:-5433}"
DB_NAME="${DB_NAME:-pizzaplaza_db}"
DB_USER="${DB_USER:-fernando}"
DB_PASS="${DB_PASS:-fernando}"

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
SEED_FILE="$SCRIPT_DIR/seed.sql"

echo "Conectando em $DB_HOST:$DB_PORT/$DB_NAME..."

PGPASSWORD="$DB_PASS" psql \
  -h "$DB_HOST" \
  -p "$DB_PORT" \
  -U "$DB_USER" \
  -d "$DB_NAME" \
  -f "$SEED_FILE"

echo "Seed concluído."
