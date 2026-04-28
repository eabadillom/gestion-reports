# Changelog

Todos los cambios importantes en este proyecto serán documentados en este archivo.

Este proyecto sigue versionado semántico (SemVer) y el formato de [Keep a Changelog](https://keepachangelog.com/es-ES/1.0.0/).

---
## [1.3.0] - 2026-04-28
### Added
- Formato de Ordenes de retiro (salida).
- Estado de cuenta del cliente.

### Changed
- La creación de los parámetros para JasperRerpots se delega a la clase `AbstractJR`.
- Las clases que heredan de `JasperBL` --> `AbstractJR` tienen acceso al objeto `jrParams`, el cual es configurado desde el constructor de `AbstractJR`.
  * * `KardexJR`
  * * `OrdenRetiroJR`
  * * `ReporteEntradasJR`
  * * `ReporteInventarioJR`
  * * `ReporteOcupacionCamaraJR`
  * * `ReporteSalidasJR`
- La clase ReporteInventarioJR agrega un método para recibir la fecha de corte de inventario, además de los parámetros de id de cliente y de planta.
- Los archivos JRXML que reciben como parámetro uno o más objetos `java.util.Date` para mostrar un periodo o fecha de corte ahora se muestran en un solo Text Field.
  * * `almacen/Entradas.jrxml`
  * * `almacen/InventarioAlmacen.jrxml`
  * * `almacen/OcupacionCamara.jrxml`
  * * `almacen/Salidas.jrxml`
  * * `contabilidad/clientes/EstadoDeCuenta.jrxml`

## [1.2.0] - 2026-04-23
### Added
- Reporte de ocupacion camara

## [1.1.0] - 2026-04-21
### Added
- Kardex

## [1.0.2] - 2026-03-31
### Added
- Actualización del controlador para MySQL 8.4.x
- Creación del archivo README.md
- Creación del archivo CHANGELOG.md
- Creación del archivo LICENSE

## Nota
Los cambios previos a esta versión (desde el inicio del proyecto) no están documentados por tratarse de la fase inicial de desarrollo.
