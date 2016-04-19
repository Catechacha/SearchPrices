SearchPrices
Consider three different web shops selling mobile phones and smartphones. 
Each store has a list of products to sell. Each product is described by: 
name of manufacturer, model, price, and shop where sold. Each store offers 
a server that provides the complete list of products, on request. All three 
shops offer the same API and use sockets to receive requests. (Tip: write 
a class that models the server and start three different instances, one for 
each store)
A server located prices maintains a list of products and the store where 
they are sold. When you start your server, contact the three stores to
 have all the lists. Every day the server contact the sellers to update his 
list of products. The server finds prices provides an API for customers who 
are looking for a product. A client sends the name of a product to the server, 
which responds with a list of products with the store and price, in ascending 
order. Implement servers and clients, using sockets. The server must be able 
to handle multiple clients at a time. The update of the list of products is 
done every 24 seconds (instead of every 24 hours).
Please note: access to the list of server products is price must be thread 
safe. Use serialization to exchange products.