import CustomNavbar from "./CustomNavbar";

const Base = ({title="Welcome To Our Webstie",children}) => {
    return (
        <div className={"container-fluid"}>
            <CustomNavbar>
            {children}
            </CustomNavbar>
        </div>
    );
};

export default Base;