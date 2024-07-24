import express, { Request, Response } from "express"
import { AppDataSource } from "./config/conecction-db"
import teamLeaderRoutes from "./routes/team-leader.routes"

const app = express()

app.use(express.json()) // Nos va a permitir recibir datos en formato JSON

app.get("/", (_, res: Response) =>{

    res.json({
        message: "Hello World"
    })
})

app.use("/api/v1/team-leader", teamLeaderRoutes)

AppDataSource
    .initialize()
    .then(() => {
        console.log("Database is connected")
        app.listen(8000,()=> {
            console.log("Server is running on port 8000")
        })
    })