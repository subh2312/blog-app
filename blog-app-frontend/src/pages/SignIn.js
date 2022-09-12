import Base from "../components/Base";
import {Button, Card, CardBody, CardHeader, Col, Container, Form, FormGroup, Input, Label, Row} from "reactstrap";
import {NavLink as ReactLink} from "react-router-dom";

const SignIn =()=>{
    return (
        <Base>
            <Container>
                <Row>
                    <Col sm={{size:8, offset:2}}>
                        <Card>
                            <CardHeader>Login</CardHeader>
                            <CardBody>
                                <Form>
                                    <FormGroup>
                                        <Label for={"email"}>Email</Label>
                                        <Input type={"email"}>Email</Input>
                                    </FormGroup>
                                    <FormGroup>
                                        <Label for={"password"}>Password</Label>
                                        <Input type={"password"}>Password</Input>
                                    </FormGroup>
                                    <Container className={"text-center"}>
                                        <Button color={"primary"}tag={ReactLink} to={"/"}>Submit</Button>
                                        <Button color={"warning"} className={"ms-2"} type={"reset"}>Reset</Button>
                                        <Button color={"danger"} tag={ReactLink} to={"/"} className={"ms-2"}>Cancel</Button>
                                    </Container>
                                </Form>
                            </CardBody>
                        </Card>
                    </Col>
                </Row>
            </Container>
        </Base>


    )
}

export default SignIn;