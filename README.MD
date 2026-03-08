# Arka — eCommerce Monolito Modular
  
Backend de eCommerce de alto rendimiento construido con **Java + Spring Boot**. El proyecto implementa una **Arquitectura Hexagonal** dentro de un **Monolito Modular**, garantizando un desacoplamiento total entre la lógica de negocio y la infraestructura.  
  
##  Stack Tecnológico  
- **Lenguaje:** Java (Spring Boot)  
- **Arquitectura:** Hexagonal (Ports & Adapters) + DDD (Domain-Driven Design)  
- **Base de Datos:** MySQL 8.4  
- **Infraestructura:** Docker & Docker Compose  
- **Cloud:** AWS (ECR → ECS)  
- **Herramientas:** phpMyAdmin para gestión de datos local/RDS.  
  
---  
  
##  Estructura del Proyecto  
Cada módulo (`product`, `order`, `user`, etc.) sigue esta jerarquía:  
- **`domain`**: Núcleo del negocio (Entidades, Value Objects, Excepciones de dominio).  
- **`application`**: Casos de uso, Handlers de comandos y Puertos (Interfaces).  
- **`infrastructure`**: Adaptadores (Controladores REST, Repositorios JPA, Clientes externos).  
  
---  
  
## Configuración y Ejecución Local  
  
El proyecto utiliza **Docker Compose** para orquestar el entorno de desarrollo, incluyendo la base de datos y herramientas de gestión.  
  
### 1. Prerrequisitos  
- Docker y Docker Compose instalados.  
- Archivos de variables de entorno: `.env.db` y `.env.back` (basados en los ejemplos de configuración).  
  
### 2. Levantar el Entorno (Full Stack Local)  
Desde la raíz del proyecto (No donde se encuentra el `docker-compose.dev.yml`, este está en .devcontainer):  
  
```bash  
docker compose -f.devcontainer/docker-compose.dev.yml up

Este comando levantará:

    MySQL (db): Expuesto en localhost:3306. Incluye healthcheck para asegurar disponibilidad.
    Backend: Expuesto en localhost:900. Monta el código fuente como volumen para desarrollo activo.
    phpMyAdmin: Expuesto en localhost:749.

3. Endpoints de Interés

    API Base: http://localhost:900/api/v0
    Gestor DB: http://localhost:749
```

Recursos Adicionales

    Postman: Importa Arka.postman_collection.json para probar los flujos de la API.
    Diagramas: Consulta la carpeta /diagramas para ver la arquitectura de capas y la conexión entre módulos.


Desarrollado con un enfoque en mantenibilidad, escalabilidad y principios SOLID.