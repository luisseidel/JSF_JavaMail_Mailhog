CREATE TABLE public.emails(
    id bigint primary key not null,
    mensagem text null,
    assunto varchar(500) null,
    email_remetente text not null,
    destinatarios text null,
    data_envio timestamp null,
);