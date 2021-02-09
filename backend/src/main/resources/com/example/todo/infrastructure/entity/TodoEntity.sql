FIND_BY_USERID =
SELECT
  *
FROM
  todo
WHERE
  user_id = :userId
ORDER by
  todo_id