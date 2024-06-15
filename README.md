# Evaluación Integradora Módulo 6
# Contenidos
## Funcionaminento: Aplicación Android que Simula el Login de aplicación de WalletDigital con validaciones y al ingresar muestra un recycleView con imagenes y datos de usuarios 
## Tecnologias: Se utiliza Kotlin, viewBinding, fragments.
## Testeo: El código se comportó de forma esperada en un Pixel 6 API 34
## Instalación: Clonar el repositorio en carpeta local y abrir con Android Studio.
> [!CAUTION]
> No hay usuarios previamente creados, por lo que debe crearse primero una cuenta para el login
## RoadMap: Funcionalidad a los items de enviar y recibir dinero.

## Estructura del proyecto
![ArquitecturaMVVM](https://github.com/Galtor-program/EvaluacionM5/assets/118318571/f8af3a8e-bb8d-4ecb-bca9-160a2b2b1ea4)
### Se utiliza Arquitectura MVVM:
## data/local
*UsuariosAdapter: Adaptador para mostrar la información de los usuarios en el RecyclerView 
*UsuariosDataSet: contiene la información de los usuarios
## data/model
*Usuarios: Atributos de usuarios
## domain/useCases
*AuthCaseUse: Autenticador para el registro de usuarios
*UsuariosListUseCase: Listado de Usuarios
## domain
*User: Atributos de User para la creación de la cuenta.
## presentation/view
*Contiene las vistas donde la única actividad es MainActivity que contiene los fragmentos
## presentation/viewmodel
*Contiene los viewmodel de las vistas activas y las Factory 

