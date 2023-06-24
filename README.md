# Food-Delivery
1. Restaurant Endpoints:
-	`GET /api/restaurants`: Retrieves a list of all restaurants.
-	`POST /api/admin/restaurants save a restaurant. (Restricted to admin role)
-	`GET /api/restaurants/{restaurantId}`: Retrieves a specific restaurant by ID.
-	`PUT /api/admin/restaurants/{restaurantId}`: Update a specific restaurant details by ID. (Restricted to admin role)
-	`DELETE / api/admin/restaurants/{restaurantId}`: Deletes a specific restaurant by ID. (Restricted to admin role)

   
2. Menu Item Endpoints:
-	`GET /api/menuItem`: Retrieves a list of all menu items.
-	`POST /api/admin/menuItem save a menu items. (Restricted to admin role)
-	`GET /api/menuItem/{menuItemId}`: Retrieves a specific menu items by ID.
-	`PUT /api/admin/menuItem/{menuItemId}`: Retrieves a specific menu items by ID. (Restricted to admin role)
-	`DELETE /api/admin/menuItem/{menuItemId}`: Deletes a specific menu items by ID. (Restricted to admin role)

3. User Endpoints:
-	`POST /api/auth/register`: register a new user.
-	`POST api/auth/login`: Retrieves a specific user by ID.

4. Order Endpoints:
-	`POST /api/orders`: Places a new order.
-	`GET /api/orders/{orderId}`: Retrieves a specific order by ID.
-	`GET api/orders`: Retrieves a list of all orders (restricted to admin role).
-	`PUT /api/admin/updateOrder`: Updates the status of a specific order (restricted to the admin role).

An admin is already created in the AdminDatabaseSeeder.java file and can be changed in the code. Log in with this admin and capture the token since it's used in all the endpoints to validate. 

In the application.properties, fill in the configurations for connecting to postgres

![image](https://github.com/PapaGede/Food-Delivery/assets/49786086/72ae808f-51c2-4008-9818-334d5eb29b61)
