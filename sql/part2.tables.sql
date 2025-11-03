create table RUBRIQUE(
    RUBNO number(3, 0) not null,
    RUBNOM varchar2(100) not null,
    RUBTYPE varchar2(25) not null,
    RUBMODE varchar2(25) not null,
    constraint pk_rubrique primary key (RUBNO)
);

create table HISTO_SAL_2(
    HISTO_DATE varchar2(50) not null,
    HISTO_EMP number(4, 0) not null,
    HISTO_RUB number(3, 0) not null,
    HISTO_VAL number(10, 2) not null,
    constraint fk_rubrique FOREIGN KEY (HISTO_RUB) REFERENCES RUBRIQUE(RUBNO)
);
