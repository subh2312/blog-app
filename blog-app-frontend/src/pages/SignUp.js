import Base from "../components/Base";
import {Button, Card, CardBody, CardHeader, Col, Container, Form, FormGroup, Input, Label, Row} from "reactstrap";
import {NavLink as ReactLink} from "react-router-dom";
import {useEffect, useState} from "react";
import {signUp} from "../services/userService";

const SignUp=()=>{
    const [data, setData]=useState({
        name:'',
        email:'',
        password:'',
        about:''
    })
    const [error, setError]=useState({
        errors:{},
        isError:false
    })



    const handleChange=(event, property)=> {
        setData({...data, [property]:event.target.value})

    }

    const resetData=()=> {
        setData({
            name:'',
            email:'',
            password:'',
            about:''
        })

    }

    const submitForm = (event) => {
        event.preventDefault();
        console.log(data);
        //data validate
        //call server api for sending data
        signUp(data)
            .then((resp)=>{
                console.log(resp);
                console.log("Success")
            })
            .catch((err)=>{
                console.log("Error : "+err)
            })
    }

    return (
        <Base>
            <Container>
                <Row>
                    <Col sm={{size:6,offset:3}}>
                        <Card>
                            <CardHeader>
                                Fill information to register!!
                            </CardHeader>
                            <CardBody>
                                <Form onSubmit={submitForm}>
                                    <FormGroup>
                                        <Label for={"name"}>Full Name</Label>
                                        <Input type={"text"}
                                               placeholder={"Enter Name"}
                                               id={"name"}
                                        onChange={(e)=>handleChange(e,'name')}
                                        value={data.name}/>
                                    </FormGroup>
                                    <FormGroup>
                                        <Label for={"email"}>Email</Label>
                                        <Input type={"email"}
                                               placeholder={"Enter Email Id"}
                                               id={"email"}
                                               onChange={(e)=>handleChange(e,'email')}
                                               value={data.email}/>

                                    </FormGroup>
                                    <FormGroup>
                                        <Label for={"password"}>Password</Label>
                                        <Input type={"password"}
                                               placeholder={"Enter Password"}
                                               id={"password"}
                                               onChange={(e)=>handleChange(e,'password')}
                                               value={data.password}/>
                                    </FormGroup>
                                    <FormGroup>
                                        <Label for={"about"}>About</Label>
                                        <Input type={"textarea"}
                                               placeholder={"Enter a description about you"}
                                               id={"about"}
                                               onChange={(e)=>handleChange(e,'about')}
                                               value={data.about}/>
                                    </FormGroup>
                                    <Container className={"text-center"}>
                                        <Button color={"primary"}>Submit</Button>
                                        <Button color={"warning"} className={"ms-2"} type={"reset"} onClick={resetData}>Reset</Button>
                                        <Button color={"danger"} tag={ReactLink} to={"/"} className={"ms-2"}>Cancel</Button>
                                    </Container>
                                </Form>

                            </CardBody>
                        </Card>
                    </Col>
                </Row>
                <br/>

            </Container>
        </Base>

       


    )
}

export default SignUp;