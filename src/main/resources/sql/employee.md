insertEmp
===
insert into `employee`(`lastName`,`email`,`gender`,`did`,`birth`) values (#lastName#,#email#,#gender#,#did#,#birth#)

updateEmp
===
update `employee` set `lastName`=#lastName#,`email`=#email#,
`gender` =gender,`did`=#did#,`birth` =#birth# where id = #id#

getEmpCount
===
select count(*) from `employee` where 1=1;

findByLimit
===
select * from `employee` limit #i#,#pageSize#;
																	
searchEmp
===
SELECT * FROM `employee` WHERE CONCAT(IFNULL(`id`,''),IFNULL(`lastName`,''),IFNULL(`email`,''),IFNULL(`did`,'')) LIKE  "%"#info#"%"

findSearchByLimit
===
SELECT
	t1.*
FROM
	(
		SELECT
			*
		FROM
			`employee`
		WHERE
			CONCAT(
				IFNULL(`id`, ''),
				IFNULL(`lastName`, ''),
				IFNULL(`email`, ''),
				IFNULL(`did`, '')
			) LIKE "%"#info#"%"
	) t1
LIMIT #i#,#pageSize#

getEmpSearchCount
===
SELECT count(*) FROM `employee` WHERE CONCAT(IFNULL(`id`,''),IFNULL(`lastName`,''),IFNULL(`email`,''),IFNULL(`did`,'')) LIKE  "%"#info#"%"