SELECT
	id,
	user_id,
	role_id,
	tenant_id,
	concat( '"', date_format( created_date, '%Y-%m-%d %H:%i:%s' ), '"' ) AS created_date,
	created_by,
	concat( '"', date_format( last_updated_date, '%Y-%m-%d %H:%i:%s' ), '"' ) AS last_updated_date,
	last_updated_by,
	version_number
FROM
	sec_user_role_rel
WHERE
	id IN ( 100001, 100002 )
ORDER BY
	id;