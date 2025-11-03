create table RUBRIQUE(
    RUBNO number(3, 0) not null,
    RUBNOM varchar2(100) not null,
    RUBTYPE varchar2(25) not null,
    RUBMODE varchar2(25) not null,
    constraint pk_rubrique primary key (RUBNO)
);

create table HISTO_SAL_2(
    HISTO_DATE DATE not null,
    HISTO_EMP number(4, 0) not null,
    HISTO_RUB number(3, 0) not null,
    HISTO_VAL number(10, 2) not null,
    constraint fk_rubrique FOREIGN KEY (HISTO_RUB) REFERENCES RUBRIQUE(RUBNO)
);

create table IRSA(
    INFE number(10, 2),
    SUPE number(10, 2),
    VAL number(2, 2)
);

create or replace view V_RUB_HISTO_SAL as 
SELECT 
    R.RUBNO,
    R.RUBNOM,
    R.RUBTYPE,
    R.RUBMODE,
    H.HISTO_DATE,
    H.HISTO_EMP,
    H.HISTO_VAL
FROM RUBRIQUE R
JOIN ON HISTO_SAL_2 h on R.RUBNO = H.HISTO_RUB;
