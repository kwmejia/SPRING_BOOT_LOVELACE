import { Request, Response } from "express";
import { AppDataSource } from "../config/conecction-db";
import { TeamLeader } from "../entity/TeamLeader";

const teamLeaderRepository = AppDataSource.getRepository(TeamLeader)

export async function getAll(req: Request, resp: Response){
    try {
        resp.json({
            data: await teamLeaderRepository.find()
        }) 
    } catch (error) {
        resp.status(500).json({
            message: error.message
        })
    }}