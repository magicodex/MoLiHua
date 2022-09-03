SELECT
	id,
	resource_type,
	access_policy,
	access_method,
	resource_path,
	frozen_flag,
	remark,
	created_lang,
	concat( '"', date_format( created_date, '%Y-%m-%d %H:%i:%s' ), '"' ) AS created_date,
	created_by,
	concat( '"', date_format( last_updated_date, '%Y-%m-%d %H:%i:%s' ), '"' ) AS last_updated_date,
	last_updated_by,
	version_number
FROM
	sec_resource
WHERE
	id IN ( 100001, 100002, 100003, 100004, 100005, 100006, 100007, 100008 )
ORDER BY
	id;