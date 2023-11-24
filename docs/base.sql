\c postgres;
DROP ROLE s3s5;
CREATE ROLE s3s5 WITH SUPERUSER LOGIN PASSWORD 's3s5';
DROP DATABASE s3s5;
CREATE DATABASE s3s5;
\c s3s5;


${pageContext.request.contextPath}/template/assets/libs/flatpickr/flatpickr.min.css