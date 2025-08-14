create table if not exists account_type (
    id bigserial primary key,
    code varchar(255) not null unique,
    title varchar(255) not null,
    description varchar(4096)
);

create table if not exists account_owner (
    id bigserial primary key,
    foreign_id bigint not null,
    foreign_type varchar(255) not null
);

alter table if exists public.account_owner
    add constraint account_owner_unq_foreignid unique (foreign_id, foreign_type);

create table if not exists accounts (
    id bigserial primary key,
    code varchar(30) not null unique,
    owner_id bigint not null,
    account_type bigint not null,
    currency varchar(10) not null,
    status varchar(255) not null,
    created_at timestamp default current_timestamp,
    created_by bigint not null,
    modified_at timestamp default current_timestamp,
    modified_by bigint not null,
    closed_at timestamp,
    closed_by bigint,
    version integer,
    CONSTRAINT fk_accounts_type FOREIGN KEY (account_type) REFERENCES account_type (id),
    CONSTRAINT fk_accounts_owner FOREIGN KEY (owner_id) REFERENCES account_owner (id)
);

create index idx_account_owner_id on accounts(owner_id);