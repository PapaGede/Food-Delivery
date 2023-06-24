# Food-Delivery
1. Restaurant Endpoints:
	`GET /api/restaurants`: Retrieves a list of all restaurants.
	`POST /api/admin/restaurants save a restaurant. (Restricted to admin role)
	`GET /api/restaurants/{restaurantId}`: Retrieves a specific restaurant by ID.
	`PUT /api/admin/restaurants/{restaurantId}`: Update a specific restaurant details by ID. (Restricted to admin role)
	`DELETE / api/admin/restaurants/{restaurantId}`: Deletes a specific restaurant by ID. (Restricted to admin role)

   
2. Menu Item Endpoints:
	`GET /api/menuItem`: Retrieves a list of all restaurants.
	`POST /api/admin/menuItem save a restaurant. (Restricted to admin role)
	`GET /api/menuItem/{menuItemId}`: Retrieves a specific restaurant by ID.
	`PUT /api/admin/menuItem/{menuItemId}`: Retrieves a specific restaurant by ID. (Restricted to admin role)
	`DELETE /api/admin/menuItem/{menuItemId}`: Deletes a specific restaurant by ID. (Restricted to admin role)

3. User Endpoints:
	`POST /api/auth/register`: register a new user.
	`POST api/auth/login`: Retrieves a specific user by ID.

4. Order Endpoints:
	`POST /api/orders`: Places a new order.
	`GET /api/orders/{orderId}`: Retrieves a specific order by ID.
	`GET api/orders`: Retrieves a list of all orders (restricted to admin role).
	`PUT /api/admin/updateOrder`: Updates the status of a specific order (restricted to the admin role).

![image](https://github.com/PapaGede/Food-Delivery/assets/49786086/72ae808f-51c2-4008-9818-334d5eb29b61)
