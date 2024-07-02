-- 코드를 작성해주세요
Select id, fish_name, length
From fish_info fi
Join fish_name_info fni on fi.fish_type = fni.fish_type
Where length = (
	Select max(length) 
	from fish_info f
	where f.fish_type = fi.fish_type)
Order by id;