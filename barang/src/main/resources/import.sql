-- insert into tbl items --
insert into items(name)values('Small Black T-Shirt') -- Id = 1
insert into items(name)values('Nike Air Jordans - Size 7') -- Id = 2
insert into items(name)values('Soccer Ball') -- Id = 3
insert into items(name)values('Lip Gloss') -- Id = 4
insert into items(name)values('Nike Medium Red Leggings') -- Id = 5
insert into items(name)values('Eye Shadow') -- Id = 6
insert into items(name)values('Chanel - CHANCE EAU FRAÎCHE Eau de Toilette') -- Id = 7
insert into items(name)values('Small Black T-Shirt') -- Id = 8
insert into items(name)values('Soccer Ball') -- Id = 9
insert into items(name)values('Nike Mercurial Superfly 8 Elite FG Firm Ground Soccer Cleat') -- Id = 10

-- insert into tbl item_Sku ---
insert into item_Sku(item_id,sku,qty,current_qty,price)values(1,'MENS-156',10,5,50) -- ID = 1
insert into item_Sku(item_id,sku,qty,current_qty,price)values(2,'NIKE-7',10,5,110.75) -- ID = 2
insert into item_Sku(item_id,sku,qty,current_qty,price)values(3,'SOC-1',10,5,25.99) -- ID = 3
insert into item_Sku(item_id,sku,qty,current_qty,price)values(4,'MU-5091',10,15,25) -- ID = 4
insert into item_Sku(item_id,sku,qty,current_qty,price)values(5,'NIKE-56',10,5,75.5) -- ID = 5
insert into item_Sku(item_id,sku,qty,current_qty,price)values(6,'MU-4129',10,5,22.85) -- ID = 6
insert into item_Sku(item_id,sku,qty,current_qty,price)values(7,'PAR-14',10,5,149.99) -- ID = 7
insert into item_Sku(item_id,sku,qty,current_qty,price)values(8,'MENS-156',10,5,50) -- ID = 8
insert into item_Sku(item_id,sku,qty,current_qty,price)values(9,'SOC-1',10,5,25.99) -- ID = 9
insert into item_Sku(item_id,sku,qty,current_qty,price)values(10,'NIKE-143',10,5,249.99) -- ID = 10

-- insert into tbl order_trans --
insert into order_trans(item_sku_id,order_id,email,qty)values(1,'RK-478','john@example.com',2)
insert into order_trans(item_sku_id,order_id,email,qty)values(2,'RK-478','john@example.com',1)
insert into order_trans(item_sku_id,order_id,email,qty)values(3,'RK-642','will@example.com',2)
insert into order_trans(item_sku_id,order_id,email,qty)values(4,'RK-238','carly@example.com',3)
insert into order_trans(item_sku_id,order_id,email,qty)values(5,'RK-238','carly@example.com',1)
insert into order_trans(item_sku_id,order_id,email,qty)values(6,'RK-238','carly@example.com',2)
insert into order_trans(item_sku_id,order_id,email,qty)values(7,'RK-912','karen@example.com',1)
insert into order_trans(item_sku_id,order_id,email,qty)values(8,'RK-239','steve@example.com',1)
insert into order_trans(item_sku_id,order_id,email,qty)values(9,'RK-149','dalton@example.com',1)
insert into order_trans(item_sku_id,order_id,email,qty)values(10,'RK-149','dalton@example.com',1)



































































