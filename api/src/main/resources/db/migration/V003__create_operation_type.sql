CREATE TABLE operation_type (
    id INTEGER,
    description VARCHAR(250),
    PRIMARY KEY(id)
);

INSERT INTO public.operation_type (id, description) VALUES(1, 'COMPRA A VISTA');
INSERT INTO public.operation_type (id, description) VALUES(2, 'COMPRA PARCELADA');
INSERT INTO public.operation_type (id, description) VALUES(3, 'SAQUE');
INSERT INTO public.operation_type (id, description) VALUES(4, 'PAGAMENTO');