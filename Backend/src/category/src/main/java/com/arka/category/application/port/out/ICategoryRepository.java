/**  
 * PUERTO DE SALIDA (Driven Port)  
 *   
 * Define QUÉ necesita la aplicación del mundo exterior (persistencia).  
 * Esta interfaz será IMPLEMENTADA por Infrastructure.  
 *   
 * ⚠️ Trabaja con entidades de DOMINIO, no con entidades JPA.  
 */
public interface ICategoryRepository {
    Category save (Category category);

    boolean existsByName (String name);
}
