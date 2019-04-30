getUserByname
===
	select * from `user` where username = #username#
	
getRoleNameByUserName
===
	select r.rolename from `user` u ,`role` r where u.roleid = r.id and u.username=#username#	
	
addUser
===
insert into `user`(`username`,`password`,`roleid`)                                                  values(#username#,#password#,#roleid#) 

findUserById
===
select * from `user` where id = #id#

updateUser
===
update `user` set `username`=#username#,`password`=#password#,
`roleid` =#roleid# where id = #id#

findByLimit
===
select * from `user` limit #i#,#pageSize#;

getUserCount	
===
select count(*) from `user` where 1=1;

findSearchByLimit
===
SELECT
	t1.*
FROM
	(
		SELECT
			*
		FROM
			`user`
		WHERE
			CONCAT(
				IFNULL(`id`, ''),
				IFNULL(`username`, ''),
				IFNULL(`roleid`, '')
			) LIKE "%"#info#"%"
	) t1
LIMIT #i#,#pageSize#

getUserSearchCount
===
SELECT count(*) FROM `user` WHERE CONCAT(IFNULL(`id`,''),IFNULL(`username`,''),IFNULL(`roleid`,'')) LIKE  "%"#info#"%"