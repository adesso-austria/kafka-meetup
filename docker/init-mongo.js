db.createUser({
	user: "meetup",
	pwd: "Secret1234!",
	roles: {
		role: "readWrite",
		db: "turbines"
	}
})
