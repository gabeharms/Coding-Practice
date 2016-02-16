class PayAppTable extends React.Component {
  constructor(props) {
    super(props);
  }

  render() {
    return (
      <table id="myTable">
        <PayAppHeaders></PayAppHeaders>
         <tbody>
          {this.props.value.palis.map(function(pali) {
            return <PayAppLineItem key={pali.id} item={pali} updateLineItem={updateLineItem}></PayAppLineItem>;
          })}
        </tbody>
      </table>
    )
  }
}
