CREATE TABLE usuario (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    senha_hash VARCHAR(255) NOT NULL,
    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE projeto (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    descricao VARCHAR(255),
    usuario_id BIGINT NOT NULL
        REFERENCES usuario(id) ON DELETE CASCADE,
    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE subestacao (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    projeto_id BIGINT NOT NULL
        REFERENCES projeto(id) ON DELETE CASCADE
);

CREATE TABLE alimentador (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    codigo VARCHAR(30) NOT NULL,
    subestacao_id BIGINT NOT NULL
        REFERENCES subestacao(id) ON DELETE CASCADE,
    tensao_nominal_kv NUMERIC(8, 3),
    UNIQUE (subestacao_id, codigo)
);

CREATE TABLE poste (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    altura_m NUMERIC(6, 2),
    material VARCHAR(30),
    status VARCHAR(20) NOT NULL DEFAULT 'ATIVO',
    projeto_id BIGINT NOT NULL
        REFERENCES projeto(id) ON DELETE CASCADE,
    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
);

CREATE TABLE transformador (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    codigo VARCHAR(40) NOT NULL UNIQUE,
    potencia_kva NUMERIC(10, 2),
    tensao_primaria_kv NUMERIC(8, 3),
    tensao_secundaria_v NUMERIC(8, 2),
    poste_id BIGINT NOT NULL
        REFERENCES poste(id) ON DELETE RESTRICT,

    CHECK (potencia_kva IS NULL OR potencia_kva > 0),
    CHECK (tensao_primaria_kv IS NULL OR tensao_primaria_kv > 0),
    CHECK (tensao_secundaria_v IS NULL OR tensao_secundaria_v > 0)
);

CREATE TABLE trecho_mt (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    codigo VARCHAR(40) NOT NULL UNIQUE,
    alimentador_id BIGINT NOT NULL
        REFERENCES alimentador(id) ON DELETE RESTRICT,
    poste_origem_id BIGINT NOT NULL
        REFERENCES poste(id) ON DELETE RESTRICT,
    poste_destino_id BIGINT NOT NULL
        REFERENCES poste(id) ON DELETE RESTRICT,

    CHECK (poste_origem_id <> poste_destino_id),

);

CREATE TABLE trecho_bt (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    codigo VARCHAR(40) NOT NULL UNIQUE,
    transformador_id BIGINT NOT NULL
        REFERENCES transformador(id) ON DELETE RESTRICT,
    poste_origem_id BIGINT NOT NULL
        REFERENCES poste(id) ON DELETE RESTRICT,
    poste_destino_id BIGINT NOT NULL
        REFERENCES poste(id) ON DELETE RESTRICT,
    CHECK (poste_origem_id <> poste_destino_id),
);

CREATE INDEX idx_subestacao_projeto
    ON subestacao(projeto_id);

CREATE INDEX idx_alimentador_subestacao
    ON alimentador(subestacao_id);

CREATE INDEX idx_poste_projeto
    ON poste(projeto_id);

CREATE INDEX idx_transformador_poste
    ON transformador(poste_id);

CREATE INDEX idx_trecho_mt_alimentador
    ON trecho_mt(alimentador_id);

CREATE INDEX idx_trecho_mt_origem
    ON trecho_mt(poste_origem_id);

CREATE INDEX idx_trecho_mt_destino
    ON trecho_mt(poste_destino_id);

CREATE INDEX idx_trecho_bt_transformador
    ON trecho_bt(transformador_id);

CREATE INDEX idx_trecho_bt_origem
    ON trecho_bt(poste_origem_id);

CREATE INDEX idx_trecho_bt_destino
    ON trecho_bt(poste_destino_id);
