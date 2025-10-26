CREATE OR REPLACE VIEW V_SALAIRE_EMP_PROCHE AS
SELECT 
    e.EMPNO ,
    e.ENAME ,
    e.JOB ,
    e.MGR ,
    e.HIREDATE ,
    e.SAL ,
    e.COMM,
    e.DEPTNO,
    h.DATE_SAL ,
    h.MONTANT as ANCIEN_MONTANT 
FROM emp e
LEFT JOIN histosal h ON e.EMPNO = h.EMPNO ;

