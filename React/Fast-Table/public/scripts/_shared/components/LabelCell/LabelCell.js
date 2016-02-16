class LabelCell extends Cell {
  constructor(props) {
    super(props)
  }
  render() {
    return(
      <td>{this.props.value}</td>
    )
  }
}

export default LabelCell;
