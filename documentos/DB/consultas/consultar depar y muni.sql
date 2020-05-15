
-- CONSULTAR A LA BASE DE DATOS EN RELACION A LOS DEPARTAMENTOS Y MUNICIPIOS

select * from MUNICIPIOS;
select * from departamentos;

-- contar la candidad de municipios de un departamento por id
select count(municipio), departamento  from municipios where departamento = 1;

-- union de las tablas departamentos y municipios con sus campos
select * from municipios as muni inner join departamentos as depar on muni.departamento = depar.id_departamento;
-- union de tablas y especifica que campos o columnas se desean mostara
select muni.municipio, depar.departamento from municipios as muni inner join departamentos as depar on muni.departamento = depar.id_departamento;

-- selecciona la cantidad de  municipios que posee sonsonate o cualquier departamento en especifico
select count( muni.municipio), depar.departamento from municipios as muni inner join departamentos as depar on muni.departamento = depar.id_departamento 
where depar.departamento = 'Sonsonate';

-- cuenta la cantidad de municipios agrupados por departamentos
select count( muni.municipio) as total, depar.departamento from municipios as muni inner join departamentos as depar on muni.departamento = depar.id_departamento 
group by departamento;