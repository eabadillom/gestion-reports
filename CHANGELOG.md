# Changelog

Todos los cambios importantes en este proyecto serán documentados en este archivo.

Este proyecto sigue versionado semántico (SemVer) y el formato de [Keep a Changelog](https://keepachangelog.com/es-ES/1.0.0/).

---
## [1.4.0] - 2026-06-19

### Added

> Se agregaron dos nuevas clases en el paquete de `com.ferbo.gestion.reports.jasper` para la obtención de los reportes de Antiguedad de saldos y Cartera de clientes

> * `AntiguedadSaldosJr`
> * `CarteraClienteJR`

> Se agregarón dos clases de tipo enumeración en el paquete `com.ferbo.util` para la extracción de la ruta de los archivos JRXML de Antiguedad de Saldos y Cartera de Clientes

> * TipoAntiguedadSaldos
> * TipoCarteraCliente

> Se agregrarón 4 nuevos archivos JRXML para la obtención de los reportes de Antiguedad de Saldos y Cartera de Clientes

> * `facturacion/AntiguedadSaldosCondensado.jrxml`
> * `facturacion/AntiguedadSaldosDesglosado.jrxml`
> * `facturacion/Concentrada.jrxml`
> * `facturacion/Desglosada.jrxml`

### Changed
> En algunas clases del paquete de 'Jasper' se agregarón nuevos métodos sobrecargados para la consulta de reportes con la opción de múltiples clientes. La modificación realizada en la firma de los métodos consiste en reemplazar el tipo de dato 'Integer' por 'List<Integer>' para el parámetro de clientes.

> * `ReporteEntradasJR`
> * `ReporteInventarioJR`
> * `ReporteOcupacionCamaraJR`
> * `ReporteSalidasJR`

> Los archivos JRXML, el parametro idCliente fue definido como java.lang.Object para permitir el manejo tanto de un solo cliente (Integer) o de múltiples clientes (List<Integer>), con el fin de aceptar diferentes tipos de entrada. Posteriormente, se agregó una validación y conversión del parámetro para que siempre sea tratado internamente como una colección de elementos, permitiendo así su utilización dentro de la consulta del reporte.

> * `almacen/Entradas.jrxml`
> * `almacen/InventarioAlmacen.jrxml`
> * `almacen/OcupacionCamara.jrxml`
> * `almacen/Salidas.jrxml`

## [1.3.2] - 2026-06-12
### Added
- Constancia de salida (ticket).

## [1.3.1] - 2026-04-30
### Changed
- En los archivos JRXML se utiliza la clase java.util.SimpleDateFormat para formatear etiquetas con rangos de fecha (por ejemplo, "Del <fecha_inicio> al <fecha_fin>"). Se configura el Locale del SimpleDateFormat para asegurar la traducción correcta de los nombres de días, meses y otros elementos de fecha en el idioma definido por el parámetro REPORT_LOCALE.
  * `almacen/Entradas.jrxml`
  * `almacen/InventarioAlmacen.jrxml`
  * `almacen/OcupacionCamara.jrxml`
  * `almacen/Salidas.jrxml`
  * `contabilidad/clientes/EstadoDeCuenta.jrxml`

## [1.3.0] - 2026-04-28
### Added
- Formato de Ordenes de retiro (salida).
- Estado de cuenta del cliente.

### Changed
- La creación de los parámetros para JasperRerpots se delega a la clase `AbstractJR`.
- Las clases que heredan de `JasperBL` --> `AbstractJR` tienen acceso al objeto `jrParams`, el cual es configurado desde el constructor de `AbstractJR`.
  * `KardexJR`
  * `OrdenRetiroJR`
  * `ReporteEntradasJR`
  * `ReporteInventarioJR`
  * `ReporteOcupacionCamaraJR`
  * `ReporteSalidasJR`
- La clase ReporteInventarioJR agrega un método para recibir la fecha de corte de inventario, además de los parámetros de id de cliente y de planta.
- Los archivos JRXML que reciben como parámetro uno o más objetos `java.util.Date` para mostrar un periodo o fecha de corte ahora se muestran en un solo Text Field.
  * `almacen/Entradas.jrxml`
  * `almacen/InventarioAlmacen.jrxml`
  * `almacen/OcupacionCamara.jrxml`
  * `almacen/Salidas.jrxml`
  * `contabilidad/clientes/EstadoDeCuenta.jrxml`

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
