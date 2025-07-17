 
## Estructura del Proyecto

Este repositorio contiene dos proyectos:

- **`/prueba`**: Proyecto API REST con Spring Boot
- **`/prueba2`**: Clases Java simples

## Requisitos
- Java 17

## Configuración y Ejecución

### Proyecto API REST (/prueba)

1. **Base de Datos**: En la raíz del proyecto se encuentra un archivo `database.sql` con todos los DDL y queries necesarios.

2. **Postman**: Se incluye un archivo `.json` de Postman para importar y consumir los endpoints rápidamente.

3. **Ejecutar**:
  ```bash
  cd prueba
  mvn spring-boot:run

```sql
INSERT INTO adasas.TMP_LLENAR_CAMPOS
(id_company, codigo_company, name_company, description_company, version_id, app_id, version, version_description, version_company_id, company_id, app_code, app_name, app_description)
VALUES(1, 'COMPANIA1', 'ARLEY COMPANY', 'COMPNIA DESCRIPTION', 1, 1, 'V1', 'V1 DESCRIPTION', 1,1, 'APP1', 'APP ALCH', 'APP DESCRIPTION COMPANY');



### Proyecto Java (/prueba2)

Contiene clases Java simples para ejecutar directamente.

## Funcionalidad de Duplicados 

El sistema implementa validación de duplicados mediante `INSERT IGNORE` en las queries del procedimiento almacenado:
En base a esto, se controla la posibilidad de duplicados, de igual forma las respectivas tablas 
tienen clampos claves marcados como unique con fin de evitar duplicidad


```sql
INSERT IGNORE INTO company (id_company, codigo_company, name_company, description_company)
VALUES (v_id_company, v_codigo_company, v_name_company, v_description_company);

INSERT IGNORE INTO application (app_id, app_code, app_name, app_description)
VALUES (v_app_id, v_app_code, v_app_name, v_app_description);

INSERT IGNORE INTO version (version_id, app_id, version, version_description)
VALUES (v_version_id, v_app_id, v_version, v_version_description);

INSERT IGNORE INTO version_company (version_company_id, company_id, version_id, version_company_description)
VALUES (v_version_company_id, v_company_id, v_version_id, CONCAT('Relación empresa-versión para ', v_name_company));
