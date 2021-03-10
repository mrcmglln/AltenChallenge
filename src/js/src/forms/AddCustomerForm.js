import React, { Component }  from 'react';
import { Formik } from 'formik'; 
import { Input, Button, } from 'antd';
import { changeStatus } from '../Client' 

class AddCustomerForm extends Component {
    render(){
        return (
             <Formik
               initialValues={{ vin: '', status: '' }}
               validate={values => {
                 const errors = {};
                
                  if(!values.vin){
                    errors.vin= 'Vehicle VIN Required'
                  }

                  if(!values.status){
                    errors.status= 'Status Required'
                  } else if (!['CONNECTED', 'DISCONNECTED'].includes(values.status)){
                    errors.status = "Status must be CONNECTED or DISCONNECTED"
                  }
                  
                 return errors;
               }}
               onSubmit={(customer, { setSubmitting }) => {
                   changeStatus(customer).then(()=> {
                      this.props.onSuccess();
                      alert(JSON.stringify(customer));
                      setSubmitting(false);
                   }) 
               }}
             >
               {({
                 values,
                 errors,
                 touched,
                 handleChange,
                 handleBlur,
                 handleSubmit,
                 isSubmitting,
                 submitForm,
                 isValid
                 /* and other goodies */
               }) => (
                 <form onSubmit={handleSubmit}>
                   <Input
                     name="vin"
                     onChange={handleChange}
                     onBlur={handleBlur}
                     value={values.vin}
                     placeholder = 'Vehicle VIN'
                   />
                   {errors.vin && touched.vin && errors.vin}
                   <Input
                     name="status"
                     onChange={handleChange}
                     onBlur={handleBlur}
                     value={values.status}
                     placeholder = 'Status'
                   />
                   {errors.status && touched.status && errors.status}
                   <Button onClick={()=>submitForm()} type="submit" disabled={isSubmitting | !isValid}>
                     Submit
                   </Button>
                 </form>
               )}
             </Formik>
        );
    }
}
export default AddCustomerForm;