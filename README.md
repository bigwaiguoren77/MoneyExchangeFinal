Descripción

Este proyecto proporciona una clase Java, APIExchangeRateProvider, para obtener las tasas de cambio de una API externa. Utiliza la librería Gson para el análisis de JSON y la API del cliente HTTP de Java 11 para realizar solicitudes de red.

Requisitos

Java 11 o posterior
Librería Gson (disponible a través de Maven o Gradle)
Empezando

Incluir la librería necesaria:

Añade la librería Gson a tu proyecto utilizando una herramienta de gestión de dependencias como Maven o Gradle.
Crear una clave API:

Regístrate para obtener una clave API de un proveedor de API de tasas de cambio como Exchangerate API.
Utilizar la clase:

Crea una instancia de APIExchangeRateProvider con tu clave API:
Java
String apiKey = "TU_CLAVE_API";
APIExchangeRateProvider proveedor = new APIExchangeRateProvider(apiKey);
Use code with caution.
content_copy
Llama al método getExchangeRate para recuperar la tasa de cambio para un par de divisas específico:
Java
double ExchangeRate = proveedor.getExchangeRate(100.0, "USD", "EUR");
System.out.println("Tasa de cambio: " + tasaCambio);

content_copy
Explicación

La clase APIExchangeRateProvider toma la clave API como argumento del constructor. El método getExchangeRate construye la URL de la solicitud de API en base a los parámetros proporcionados (cantidad, moneda de origen y moneda destino). Luego, utiliza la API del cliente HTTP de Java para obtener los datos de la tasa de cambio en formato JSON. Finalmente, analiza la respuesta JSON usando Gson y extrae la tasa de cambio deseada para la moneda destino.

Manejo de Errores

El método getExchangeRate lanza excepciones IOException e InterruptedException en caso de errores de red o interrupciones durante la llamada a la API. También verifica el formato de la respuesta y lanza una excepción si no se recibe el tipo de contenido esperado (application/json).

Nota:

Este ejemplo utiliza un proveedor de API específico para fines de demostración. Es posible que debas modificar el código según la documentación y la estructura de la API proporcionada por tu proveedor de tasa de cambio elegido.

Licencia

Este proyecto está bajo licencia Apache License 2.0.
