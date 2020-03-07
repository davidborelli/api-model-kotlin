CREATE EXTENSION IF NOT EXISTS "pgcrypto";

create table atividade (
    guid char(36) not null,
    id integer,
    nome text not null,
    cor text null,
    operacional bool null,
    financeiro bool null,
    apontamento bool null,
    ativo bool not null,
    criado varchar(20) null,
    atualizado varchar(20) null,
    excluido varchar(20) null,
    tempo_improdutivo bool null,
    checklist bool null,
    solicitar_km bool null,
    primary key (guid)
);

create table almoxarifado (
    guid char(36) not null,
    id integer not null unique,
    nome text not null,
    ativo bool not null,
    cor text null,
    criado varchar(20) null,
    atualizado varchar(20) null,
    excluido varchar(20) null,
    primary key (guid)
);


create table tipo_patrimonio (
    guid char(36) not null,
    id integer not null unique,
    nome text not null,
    ativo bool not null,
    cor text null,
    criado varchar(20) null,
    atualizado varchar(20) null,
    excluido varchar(20) null,
    primary key (guid)
);


create table patrimonio (
    guid char(36) not null,
    id integer not null unique,
    nome text not null,
    identificador text null,
    numero_serial text null,
    ativo bool not null,
    almoxarifado_guid char(36) not null,
    localizacao text null,
    numero_patrimonio int null,
    criado varchar(20) null,
    atualizado varchar(20) null,
    excluido varchar(20) null,
    tipo_patrimonio_guid char(36) not null,
    primary key (guid)
);

create table evento (
    guid char(36) not null,
    id integer not null unique,
    nome text not null,
    tempo_improdutivo decimal(10,0) null,
    criado varchar(20) null,
    atualizado varchar(20) null,
    excluido varchar(20) null,
    os bool null,
    liquidacao bool null,
    ativo bool not null,
    primary key (guid)
);

create table campo_personalizado (
    guid char(36) not null,
    id integer not null unique,
    tipo_campo text null ,
    descricao text null,
    referencia_guid char(36) null,
    referencia_tipo text null,
    criado varchar(20) null,
    atualizado varchar(20) null,
    excluido varchar(20) null,
    primary key (guid)
);


create table campo_personalizado_valor (
    guid char(36) not null,
    id integer not null unique,
    campo_personalizado_guid char(36) not null,
    valor text null,
    referencia_guid char(36) null,
    referencia_tipo text null,
    criado varchar(20) null,
    atualizado varchar(20) null,
    excluido varchar(20) null,
    primary key (guid)
);

create table tipo_equipamento (
    guid char(36) not null,
    id integer not null unique,
    nome text not null,
    tempo_estimado decimal(10,0) null,
    ativo bool not null,
    criado varchar(20) null,
    atualizado varchar(20) null,
    excluido varchar(20) null,
    primary key (guid)
);


create table perfil (
    guid char(36) not null,
    id integer,
    nome text not null,
    ativo bool not null,
    acesso_web text null,
    acesso_app text null,
    configuracao_web text null,
    configuracao_app text null,
    cor text null,
    criado varchar(20) null,
    atualizado varchar(20) null,
    excluido varchar(20) null,
    primary key (guid)
);


create table usuario (
    guid char(36) not null,
    id integer,
    nome text not null,
    usuario text not null unique,
    data_nascimento text,
    senha text not null,
    telefone1 text null,
    telefone2 text null,
    usuario_web bool null,
    email text null,
    ativo bool not null,
    cor text null,
    perfil_guid char(36),
    criado varchar(20) null,
    atualizado varchar(20) null,
    excluido varchar(20) null,
    primary key (guid)
    );


create table patrimonio_historico (
    guid char(36) not null,
    id integer not null unique,
    patrimonio_guid char(36) not null,
    patrimonio_almoxarifado_guid char(36) not null,
    data text not null,
    localizacao text null,
    usuario_guid char(36) not null,
    observacao text null,
    criado varchar(20) null,
    atualizado varchar(20) null,
    excluido varchar(20) null,
    primary key (guid)
);

create table cliente (
    guid char(36) not null,
    id integer,
    nome text not null,
    razao_social text not null,
    ie_rg text null,
    cnpj_cpf text not null,
    endereco text not null,
    telefone text null,
    cep text null,
    cidade text not null,
    senha_acesso text null,
    ativo bool not null,
    cor text null,
    criado varchar(20) null,
    atualizado varchar(20) null,
    excluido varchar(20) null,
    primary key (guid)
);


create table equipamento (
    guid char(36) not null,
    id integer not null unique,
    nome text not null,
    tipo_equipamento_guid char(36) not null,
    marca text null,
    identificador text null,
    modelo text null,
    ano_modelo int null,
    ano_fabricacao int null,
    cliente_guid char(36) not null,
    criado varchar(20) null,
    atualizado varchar(20) null,
    excluido varchar(20) null,
    primary key (guid)
);


create table tipo_pagamento (
    guid char(36) not null,
    id integer not null unique,
    nome text not null,
    consome_saldo bool null,
    solicita_identificador bool null,
    ativo bool not null,
    cor text null,
    criado varchar(20) null,
    atualizado varchar(20) null,
    excluido varchar(20) null,
    primary key (guid)
);


create table observacao (
    guid char(36) not null,
    id integer not null unique,
    nome text not null,
    inventario bool null,
    ativo bool not null,
    criado varchar(20) null,
    atualizado varchar(20) null,
    excluido varchar(20) null,
    primary key (guid)
);


create table despesa (
    guid char(36) not null,
    id integer not null unique,
    valor decimal(10,0) not null,
    data text null,
    latitude text null,
    longitude text null,
    anotacao text null,
    num_forma_pagamento text null,
    cliente_guid char(36) not null,
    evento_guid char(36) not null,
    usuario_guid char(36) not null,
    tipo_pagamento_guid char(36) not null,
    criado varchar(20) null,
    atualizado varchar(20) null,
    excluido varchar(20) null,
    primary key (guid)
);


create table relatorio_despesa (
    guid char(36) not null,
    id integer not null unique,
    data_criacao text null,
    data_finalizacao text null,
    anotacao_despesa text null,
    data_expiracao_pagamento text null,
    anotacao_pagamento text null,
    status text null,
    hash text null,
    criado varchar(20) null,
    atualizado varchar(20) null,
    excluido varchar(20) null,
    cliente_guid char(36) not null,
    usuario_guid char(36) not null,
    primary key (guid)
);


create table despesa_item (
    guid char(36) not null,
    id integer not null unique,
    data text null,
    anotacao text null,
    quantidade decimal(10,0) null,
    valor_unitario decimal(10,0) null,
    valor_total decimal(10,0) null,
    evento_guid char(36) not null,
    usuario_guid char(36) not null,
    relatorio_despesa_guid char(36) not null,
    despesa_guid char(36) not null,
    criado varchar(20) null,
    atualizado varchar(20) null,
    excluido varchar(20) null,
    primary key (guid)
);


create table empresa (
    guid char(36) not null,
    id integer not null unique,
    razao_social text null,
    nome_fantasia text null,
    endereco text not null,
    cidade text null,
    cnpj text null,
    ie text null,
    telefone text null,
    site text null,
    cep text null,
    nome_logo text null,
    criado varchar(20) null,
    atualizado varchar(20) null,
    excluido varchar(20) null,
    primary key (guid)
);


create table balanco (
    guid char(36) not null,
    id integer not null unique,
    data text null,
    valor decimal(10,0) null,
    usuario_guid char(36) not null,
    tipo_pagamento_guid char(36) not null,
    criado varchar(20) null,
    atualizado varchar(20) null,
    excluido varchar(20) null,
    primary key (guid)
);

create table extrato (
    guid char(36) not null,
    id integer not null unique,
    data_liberacao text null,
    tipo char(1) null,
    valor decimal(10,0) null,
    despesa_guid char(36) not null,
    tipo_pagamento_guid char(36) not null,
    status_pagamento char(1) null,
    data_criacao text null,
    usuario_criou_guid char(36) not null,
    data_aprovacao text null,
    usuario_aprovou char(36) null,
    usuario_guid char(36) null,
    informacao text null,
    observacao text null,
    criado varchar(20) null,
    atualizado varchar(20) null,
    excluido varchar(20) null,
    primary key (guid)
);


create table veiculo (
    guid char(36) not null,
    id integer null,
    marca text null,
    ano_fabricacao int null,
    modelo text null,
    ano_modelo int null,
    placa text null,
    tempo_estimado decimal(10,0) null,
    cliente_guid char(36) null,
    equipamento_guid char(36) null,
    criado varchar(20) null,
    atualizado varchar(20) null,
    excluido varchar(20) null,
    primary key (guid)
);


create table servico (
    guid char(36) not null,
    id integer not null unique,
    tempo_inicio text null,
    tempo_fim text null,
    tempo_inicio_lat text null,
    tempo_fim_lat text null,
    tempo_inicio_lgt text null,
    tempo_fim_lgt text null,
    status char(1) null,
    cliente_guid char(36) not null,
    usuario_guid char(36) not null,
    atividade_guid char(36) not null,
    tag text null,
    criado varchar(20) null,
    atualizado varchar(20) null,
    excluido varchar(20) null,
    veiculo_guid char(36) not null,
    primary key (guid)
);


create table foto_personalizado (
    guid char(36) not null,
    id integer not null unique,
    campo_personalizado_guid char(36) null,
    referencia_guid char(36) null,
    referencia_tipo text null,
    latitude text null,
    longitude text null,
    foto_extensao text null,
    fix_foto_extensao text null,
    foto_nome text null,
    fix_foto_nome text null,
    foto_caminho text null,
    fix_foto_caminho text null,
    mensagem text null,
    manualmente bool null,
    observacao text null,
    status_checklist bool null,
    status_fix bool null,
    criado varchar(20) null,
    atualizado varchar(20) null,
    excluido varchar(20) null,
    primary key (guid)
);


create table servico_interrupcao (
    guid char(36) not null,
    id integer not null unique,
    tempo_inicial text null,
    tempo_final text null,
    tempo_inicial_lat text null,
    tempo_final_lat text null,
    tempo_inicial_lgt text null,
    tempo_final_lgt text null,
    servico_guid char(36) not null,
    criado varchar(20) null,
    atualizado varchar(20) null,
    excluido varchar(20) null,
    primary key (guid)
);


create table rota (
    guid char(36) not null,
    id integer not null unique,
    latitude text null,
    longitude text null,
    hora text null,
    status char(1) null,
    usuario_guid char(36) not null,
    criado varchar(20) null,
    atualizado varchar(20) null,
    excluido varchar(20) null,
    primary key (guid)
);


create table apontamento (
    guid char(36) not null,
    id integer not null unique,
    tempo_inicial text null,
    tempo_final text null,
    tempo_inicial_lat text null,
    tempo_inicial_lgt text null,
    tempo_final_lat text null,
    tempo_final_lgt text null,
    status char(1) null,
    cliente_guid char(36) not null,
    usuario_guid char(36) not null,
    tempo_inicial_original text null,
    tempo_final_original text null,
    anotacao text null,
    criado varchar(20) null,
    atualizado varchar(20) null,
    excluido varchar(20) null,
    primary key (guid)
);


create table apontamento_evento (
    guid char(36) not null,
    id integer not null unique,
    data_hora text null,
    latitude text null,
    longitude text null,
    apontamento_guid char(36) not null,
    evento_guid char(36) not null,
    tempo_inicial text null,
    tempo_final text null,
    tempo_inicial_original text null,
    tempo_final_original text null,
    observacao text null,
    criado varchar(20) null,
    atualizado varchar(20) null,
    excluido varchar(20) null,
    primary key (guid)
);

create table campo_personalizado_lista (
    guid char(36) not null,
    id integer not null unique,
    campo_personalizado_guid char(36) not null,
    descricao varchar(30) null,
    criado varchar(20) null,
    atualizado varchar(20) null,
    excluido varchar(20) null,
    tipo_campo text null,
    valores text null,
    primary key (guid)
);

create table agenda (
    guid char(36) not null,
    id integer,
    data varchar(20) null,
    usuario_guid char(36) not null,
    criado varchar(20) null,
    atualizado varchar(20) null,
    excluido varchar(20) null,
    primary key(guid)
);

create table agenda_item (
    guid char(36) not null,
    id integer,
    cliente_guid char(36),
    veiculo_guid char(36) null,
    agenda_guid char(36) not null,
    atividade_guid char(36) null,
    tipo varchar(45) null,
    status varchar(45) null,
    observacao text null,
    criado varchar(20) null,
    atualizado varchar(20) null,
    excluido varchar(20) null,
    primary key(guid)
);

create table parametros (
    guid char(36) not null,
    id integer,
    agenda_tem_domingo boolean,
    agenda_lancamento_retro boolean,
    criado varchar(20) null,
    atualizado varchar(20) null,
    excluido varchar(20) null,
    primary key(guid)
);

alter table agenda_item add foreign key (agenda_guid) references agenda(guid);
alter table agenda_item add foreign key (cliente_guid) references cliente(guid);
alter table agenda_item add foreign key (veiculo_guid) references veiculo(guid);
alter table agenda_item add foreign key (atividade_guid) references atividade(guid);

alter table servico_interrupcao add foreign key (servico_guid) references servico(guid);

alter table servico add foreign key (cliente_guid) references cliente(guid);
alter table servico add foreign key (usuario_guid) references usuario(guid);
alter table servico add foreign key (atividade_guid) references atividade(guid);
alter table servico add foreign key (veiculo_guid) references veiculo(guid);

alter table extrato add foreign key (despesa_guid) references despesa(guid);
alter table extrato add foreign key (tipo_pagamento_guid) references tipo_pagamento(guid);
alter table extrato add foreign key (usuario_criou_guid) references usuario(guid);

alter table equipamento add foreign key (tipo_equipamento_guid) references tipo_equipamento(guid);
alter table equipamento add foreign key (cliente_guid) references cliente(guid);

alter table patrimonio add foreign key (almoxarifado_guid) references almoxarifado(guid);
alter table patrimonio add foreign key (tipo_patrimonio_guid) references tipo_patrimonio(guid);

alter table usuario add foreign key (perfil_guid) references perfil(guid);

alter table despesa add foreign key (cliente_guid) references cliente(guid);
alter table despesa add foreign key (evento_guid) references evento(guid);
alter table despesa add foreign key (usuario_guid) references usuario(guid);
alter table despesa add foreign key (tipo_pagamento_guid) references tipo_pagamento(guid);

alter table patrimonio_historico add foreign key (patrimonio_guid) references patrimonio(guid);
alter table patrimonio_historico add foreign key (usuario_guid) references usuario(guid);

alter table rota add foreign key (usuario_guid) references usuario(guid);

alter table balanco add foreign key (usuario_guid) references usuario(guid);
alter table balanco add foreign key (tipo_pagamento_guid)  references tipo_pagamento(guid);

alter table despesa_item add foreign key (evento_guid) references evento(guid);
alter table despesa_item add foreign key (usuario_guid) references usuario(guid);
alter table despesa_item add foreign key (relatorio_despesa_guid) references relatorio_despesa(guid);
alter table despesa_item add foreign key (despesa_guid) references despesa(guid);

-- alter table veiculo add foreign key (cliente_guid) references cliente(guid);
-- alter table veiculo add foreign key (equipamento_guid) references equipamento(guid);

alter table relatorio_despesa add foreign key (cliente_guid) references cliente(guid);
alter table relatorio_despesa add foreign key (usuario_guid) references usuario(guid);

alter table apontamento add foreign key (cliente_guid) references cliente(guid);
alter table apontamento add foreign key (usuario_guid) references usuario(guid);

alter table apontamento_evento add foreign key (apontamento_guid) references apontamento(guid);
alter table apontamento_evento add foreign key (evento_guid) references evento(guid);

alter table campo_personalizado_valor add foreign key (campo_personalizado_guid) references campo_personalizado(guid);

alter table campo_personalizado_lista add foreign key (campo_personalizado_guid) references campo_personalizado(guid);

alter table foto_personalizado add foreign key (campo_personalizado_guid) references campo_personalizado(guid);

insert into perfil values (gen_random_uuid(), 0, 'ADMINISTRADOR', true, 'sim', 'sim', 'sim', 'sim', '#ffff', null, null, null);
insert into usuario values (gen_random_uuid(), 0, 'Administrador', 'ADMIN', null, '$2a$10$3D2EyIjk4Gb7ILTPX5oh5e7lLIb7NnXgMHs/mtWDEbXISVPcX.7hC', null, null, true, 'admin@gimb.com.br', true, '#ffff', (select perfil.guid from perfil where perfil.nome = 'ADMINISTRADOR'), null, null, null);

insert into parametros values(gen_random_uuid(), 1, true, true);
